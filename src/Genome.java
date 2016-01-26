import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 26.01.2016.
 */
public class Genome {


     List<String> DNA = new ArrayList<>();
     List<Integer> numberChain = new ArrayList<>();
    List<String> operatorChain = new ArrayList<>();
    double result;
     double fitness;
     String dnaString;
    boolean valid;


    public Genome()
    {
        valid = true;

        numberChain.clear();
        operatorChain.clear();
        DNA.clear();
        result = Double.POSITIVE_INFINITY;
        fitness = Double.NEGATIVE_INFINITY;
        dnaString = "";


    }





    public void setUpGenome()
    {
        try {
            calculateResult();
        }
        catch (Exception e)
        {
            valid = false;
        }

        calculateFitness();
        generateDnaString();
    }

    public void generateRandomDNA()
    {

        //generateRandomNumbers
        for (int i = References.defaultNumberChainLength; i != 0; i--)
        {
            numberChain.add((int) Math.round(Math.random()*9));
        }
        //GenerateRandomMathOperators
        for (int i = References.defaultNumberChainLength -1; i != 0; i--)
        {
            String operatorToAdd = "";
            int rdm = (int) Math.round(Math.random() * 3);
            switch (rdm)
            {
                case 0:
                    operatorToAdd = "+";
                    break;
                case 1:
                    operatorToAdd = "-";
                    break;
                case 2:
                    operatorToAdd = "*";
                    break;
                case 3:
                    operatorToAdd = "/";
                    break;
            }
            operatorChain.add(operatorToAdd);
        }
        // combine Number + Math Ops to DNA
        for (int i = 0; i < numberChain.size() -1 ;i++)
        {
            DNA.add(String.valueOf(numberChain.get(i)));
            DNA.add(operatorChain.get(i));
        }
        DNA.add(String.valueOf(numberChain.get(numberChain.size()-1)));




    }

    public void generateDnaString()
    {
        dnaString = "";

        for (int i = 0;i < DNA.size(); i++)
        {
            dnaString += DNA.get(i);
        }

    }

    public void calculateFitness()
    {
        fitness = Math.abs(result - Double.valueOf(References.aim));

    }

    public void calculateResult()
    {
        final List<String> calc = new ArrayList<String>();
        //Copy DNA to calc
        for (int i = 0;i < DNA.size();i++)
        {
            calc.add(DNA.get(i));
        }
        //Punktrechnugn
        for (int i = 0; i < calc.size() -1;)
        {
            switch (calc.get(i + 1))
            {
                case "*":
                    calc.set(i , String.valueOf(Integer.valueOf(calc.get(i)) *  Integer.valueOf(calc.get(i + 2))));
                    calc.remove(i+2);
                    calc.remove(i+1);
                    break;
                case "/":
                    if ((Integer.valueOf(calc.get(i + 2)) == 0))
                    {
                        throw new RuntimeException("Div by Zero");
                    }
                    calc.set(i , String.valueOf(Integer.valueOf(calc.get(i)) /  Integer.valueOf(calc.get(i + 2))));
                    calc.remove(i+2);
                    calc.remove(i+1);
                    break;
                default:
                    i = i + 2;
            }
        }
        //Strichrechnung
        float r = Float.valueOf(calc.get(0));
        for (int i = 0; i < calc.size() -1; i = i +2)
        {
            switch (calc.get(i + 1))
            {
                case "+":
                    r = r + Integer.valueOf(calc.get(i + 2));
                    break;
                case "-":
                    r = r - Integer.valueOf(calc.get(i + 2));
                    break;
                default:
                    throw new RuntimeException("Something went completley Wrong this should never happen");
            }
        }
        result = r;

    }
}
