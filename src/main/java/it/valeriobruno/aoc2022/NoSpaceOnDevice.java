package it.valeriobruno.aoc2022;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoSpaceOnDevice {

    public NoSpaceOnDevice()
    {
        DirNode node = new DirNode("/");
        this.root = node;
        selectedNode = root;
    }
    abstract class Node{
        public Node(String name) {
            this.name = name;
        }

        String name;

        abstract boolean isDir();


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return name.equals(node.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    class DirNode extends Node {
        public DirNode(String name) {
            super(name);
            subDirs = new HashMap<>();
            files = new HashMap<>();
        }

        boolean isDir() {
            return true;
        }

        DirNode parent;
        HashMap<String, DirNode> subDirs;
        HashMap<String, FileNode> files;

        DirNode addSubdir(String name) {
            if (subDirs.putIfAbsent(name, new DirNode(name)) != null)
                System.out.printf("Folder already exists: %s\n", name);
            subDirs.get(name).parent = this;
            return subDirs.get(name);
        }

        public void addFile(FileNode file) {
            if (files.putIfAbsent(file.name, file) != null)
                System.out.printf("File already exists: %s\n", name);

        }

        long totalSize()
        {
            long fileSize = files.values().stream().map( f -> f.size).reduce(Long::sum).orElse(0L);
            long dirSize = subDirs.values().stream().map( d -> d.totalSize()).reduce(Long::sum).orElse(0L);

            return fileSize+dirSize;
        }

        public DirNode getDir(String dirName) {
            var dir = subDirs.get(dirName);
            if(dir == null)
                throw new RuntimeException("invalid dir");
            return dir;
        }

        public DirNode getParentDir() {
            if(parent == null)
                throw new RuntimeException("Null Parent");
            return parent;
        }
    }

    class FileNode extends Node
    {
        long size;

        public FileNode(String name,long size) {
            super(name);
            this.size = size;
        }

        @Override
        boolean isDir() {
            return false;
        }
    }

    DirNode root;
    DirNode selectedNode;

    Pattern cdPattern = Pattern.compile("\\$ cd (.+)" );
    Pattern filePattern = Pattern.compile("(\\d+) (.+)" );

    void parseLine(String line)
    {
        var cdMatcher = cdPattern.matcher(line);
        var fileMatcher = filePattern.matcher(line);
        if(cdMatcher.matches())
            executeChangeDir(cdMatcher);
        else if(fileMatcher.matches())
            addFile(fileMatcher);
        else if (line.startsWith("dir "))
        {
            selectedNode.addSubdir(line.substring(4));
        }
        else
            System.err.printf("Invalid command: %s\n",line);
    }

    private void addFile(Matcher fileMatcher) {
        String filename = fileMatcher.group(2);
        long size = Long.parseLong(fileMatcher.group(1));
        FileNode file = new FileNode(filename,size);
        selectedNode.addFile(file);
    }


    private void executeChangeDir(Matcher cdMatcher) {
        var dirName= cdMatcher.group(1);
        if(dirName.equals("/"))
        {
            selectedNode = root;
        }
        else if(dirName.equals(".."))
            selectedNode = selectedNode.getParentDir();
        else {
            selectedNode = selectedNode.getDir(dirName);
        }
    }

    long totalSizeQ1()
    {
        visitNode(root,"/");
        System.out.println(totalSizes);

        return totalSizes.values().stream().filter( s -> s< 100000).reduce(Long::sum).orElse(0L);

    }

    long totalSizeQ2()
    {
        long initialAvailableSpace = 70000000L - root.totalSize();
        long needToClean = 30000000 - initialAvailableSpace;

        return totalSizes.values().stream().filter( s -> s>= needToClean).min(Comparator.naturalOrder()).orElseThrow();
    }

    HashMap<String,Long> totalSizes = new HashMap<>();
     void visitNode(DirNode dir,String path)
     {
         dir.subDirs.values().forEach(d -> visitNode(d,path+"/"+d.name));
         totalSizes.put(path, dir.totalSize());
     }


    public static void main(String[] args) throws IOException {
        NoSpaceOnDevice ns = new NoSpaceOnDevice();
        Files.readLines(new File("input-2022-7.txt"), Charset.forName("UTF-8")).forEach(ns::parseLine);
        System.out.println(ns.totalSizeQ1());
        System.out.println(ns.totalSizeQ2());

    }
}
