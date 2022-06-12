package it.valeriobruno.tc;

public class StepHopJumpEasy {


    public String solve(String level) {

        if(level.length() == 2)
            return "S";
        else if(level.length() == 3)
            return "H";
        else if(level.length() == 4)
            return "J";

        String path="";

        if( level.charAt(1) == '-' ) {

            path = solve(level.substring(1));
            if(!path.equals(""))
                return "S"+path;


        }
        if(level.charAt(2) == '-' )
        {

            path = solve(level.substring(2));
            if(!path.equals(""))
                return "H"+path;


        }
        if(level.charAt(3) == '-' )
        {

            path = solve(level.substring(3));
            if(!path.equals(""))
                return "H"+path;


        }

        return "";
    }
}
