import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 26.01.2016.
 */
public class Mutate {

    static double lengthChance = References.lengthMutationChance;
    static double numberChance = References.numberMutationChance;
    static double operChance = References.operatorMutationChance;

    public static List<String> mutate(List<String> parental)
    {



        List<String> newDNA = new ArrayList<String>();

        //Actual Mutation

        for (int i = 0; i < parental.size(); i++)
        {
            double tmpRdm = Math.random();
            if ((tmpRdm <= References.numberMutationChance) & ((i % 2) == 0))
            {
                newDNA.add(String.valueOf(Math.round(Math.random() * 9)));
            }

            if ((tmpRdm >= References.numberMutationChance) & ((i % 2) == 0))
            {
                newDNA.add(parental.get(i));
            }
            tmpRdm = Math.random();
            if (tmpRdm <= References.operatorMutationChance)
            {
                String opToAdd = "";
                int rdm = (int) Math.round(Math.random()*3);

                switch (rdm)
                {
                    case 0:
                        opToAdd = "+";
                        break;
                    case 1:
                        opToAdd = "-";
                        break;
                    case 2:
                        opToAdd = "*";
                        break;
                    case 3:
                        opToAdd = "/";
                        break;
                }
                newDNA.add(opToAdd);
            }
            if ((tmpRdm >= References.operatorMutationChance) & ((i % 2) != 0))
            {
                newDNA.add(parental.get(i));
            }



        }

        // length Mutation

        if (Math.random() <= lengthChance)
        {
            if (Math.random() < 0.5)
            {
                String opToAdd = "";
                int rdm = (int) Math.round(Math.random()*3);

                switch (rdm)
                {
                    case 0:
                        opToAdd = "+";
                        break;
                    case 1:
                        opToAdd = "-";
                        break;
                    case 2:
                        opToAdd = "*";
                        break;
                    case 3:
                        opToAdd = "/";
                        break;
                }
                newDNA.add(opToAdd);

                newDNA.add(String.valueOf(Math.round(Math.random()*9)));
            }
           else
            {
                if (newDNA.size() > 3)
                {
                    newDNA.remove(newDNA.size()-1);
                    newDNA.remove(newDNA.size()-1);

                }
            }
        }



        return newDNA;

    }

    public static List<String> shortMutate(List<java.lang.String> parental)
    {
        List<String> newDNA = new ArrayList<String>();

        //Actual Mutation

        for (int i = 0; i < parental.size(); i++)
        {
            double tmpRdm = Math.random();
            if ((tmpRdm <= References.numberMutationChance) & ((i % 2) == 0))
            {
                newDNA.add(String.valueOf(Math.round(Math.random() * 9)));
            }

            if ((tmpRdm >= References.numberMutationChance) & ((i % 2) == 0))
            {
                newDNA.add(parental.get(i));
            }
            tmpRdm = Math.random();
            if (tmpRdm <= References.operatorMutationChance)
            {
                String opToAdd = "";
                int rdm = (int) Math.round(Math.random()*3);

                switch (rdm)
                {
                    case 0:
                        opToAdd = "+";
                        break;
                    case 1:
                        opToAdd = "-";
                        break;
                    case 2:
                        opToAdd = "*";
                        break;
                    case 3:
                        opToAdd = "/";
                        break;
                }
                newDNA.add(opToAdd);
            }
            if ((tmpRdm >= References.operatorMutationChance) & ((i % 2) != 0))
            {
                newDNA.add(parental.get(i));
            }



        }

        // length Mutation

       for (int i = (int) Math.round(Math.random() * 3);i != 0; i--) {
           if (Math.random() < 0.5) {
               String opToAdd = "";
               int rdm = (int) Math.round(Math.random() * 3);

               switch (rdm) {
                   case 0:
                       opToAdd = "+";
                       break;
                   case 1:
                       opToAdd = "-";
                       break;
                   case 2:
                       opToAdd = "*";
                       break;
                   case 3:
                       opToAdd = "/";
                       break;
               }
               newDNA.add(opToAdd);

               newDNA.add(String.valueOf(Math.round(Math.random() * 9)));
           } else {
               if (newDNA.size() > 3) {
                   newDNA.remove(newDNA.size() - 1);
                   newDNA.remove(newDNA.size() - 1);

               }
           }
       }




        return newDNA;


    }

}
