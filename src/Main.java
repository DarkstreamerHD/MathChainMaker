import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 26.01.2016.
 */
public class Main {

    public static Genome fittest;

    public static void main(String[] args) {
        /**  Genome nG = new Genome();
         nG.generateRandomDNA();
         nG.setUpGenome();
         System.out.println(nG.DNA);
         System.out.println(nG.result);
         */

        List<Genome> ParentalGeneration = createParentalGeneration();
        fittest = sortListByFitness(ParentalGeneration);
        System.out.println("Die beste Gleichung war: " + fittest.dnaString + " = " + fittest.result);
        System.out.println("Ihre Fitness betrug: " + fittest.fitness);
        System.out.println(ParentalGeneration.size());

        for (int i = 0; i < References.maxGenAmount; i++) {
            List<Genome> FilialGeneration = createFilialGeneration(fittest);
            fittest = sortListByFitness(FilialGeneration);
            System.out.println("Die beste Gleichung war: " + fittest.dnaString + " = " + fittest.result);
            System.out.println("Ihre Fitness betrug: " + fittest.fitness);
            System.out.println("Generation" + i);
            FilialGeneration.clear();

            if (fittest.fitness == 0) {
                i = References.maxGenAmount;
                System.out.println("Solution found: " + fittest.dnaString + " = " + References.aim);
                Mutate.lengthChance = 1;
            }
        }

     /*   for (int i = 0; i < References.maxGenAmount;i++)
        {
            List<Genome> FilialGeneration = createShortFilialGeneration(fittest);
            fittest = sortListByDnaLengthAndFitness(FilialGeneration);
            System.out.println("Die kürzeste Gleichung war: " + fittest.dnaString + " = " + fittest.result);
            System.out.println("Ihre Länge betrug: " + fittest.DNA.size());
            System.out.println("Generation" + i);
            FilialGeneration.clear();
        }*/
    }


    public static List<Genome> createParentalGeneration()
    {
        List<Genome> genomeList = new ArrayList();

        for (int i = References.generationSize; i != 0; i--) {

            Genome newGenome = new Genome();
            newGenome.generateRandomDNA();
            newGenome.setUpGenome();
            if (newGenome.valid) {
                genomeList.add(newGenome);
            }
            else
            {
                i++;
            }
        }
        return genomeList;
    }

    public static List<Genome> createFilialGeneration(Genome fittestOfLastGeneration)
    {
        List<Genome> genomeList = new ArrayList<>();

        genomeList.add(fittest);

        for (int i = 0; i < References.generationSize; i++)
        {
            List<String> mutatedDNA;

            mutatedDNA = Mutate.mutate(fittestOfLastGeneration.DNA);

            Genome newGenome = new Genome();
            newGenome.DNA = mutatedDNA;
            newGenome.setUpGenome();

            if (newGenome.valid) {
                genomeList.add(newGenome);
            }
            else
            {
                i++;
            }
        }

        return genomeList;
    }

    public static List<Genome> createShortFilialGeneration(Genome fittestOfLastGeneration)
    {
        List<Genome> genomeList = new ArrayList<>();

        genomeList.add(fittest);

        for (int i = 0; i < References.generationSize * 100; i++)
        {
            List<String> mutatedDNA;

            mutatedDNA = Mutate.shortMutate(fittestOfLastGeneration.DNA);

            Genome newGenome = new Genome();
            newGenome.DNA = mutatedDNA;
            newGenome.setUpGenome();

            if (newGenome.valid & newGenome.fitness == 0) {
                genomeList.add(newGenome);
            }
            else
            {
                i++;
            }
        }

        return genomeList;
    }

    public static Genome sortListByFitness(List<Genome> inputList)
    {
        Genome fittest = inputList.get(0);
        for (int i = 1;i < inputList.size(); i++)
        {
            if (fittest.fitness > inputList.get(i).fitness )
            {
                fittest = inputList.get(i);
            }
        }

        return fittest;

    }


    public static Genome sortListByDnaLengthAndFitness(List<Genome> inputList)
    {
        Genome fittest = inputList.get(0);
        for (int i = 1;i < inputList.size(); i++)
        {
            if ( fittest.DNA.size()< fittest.DNA.size() )
            {
                fittest = inputList.get(i);
            }
        }

        return fittest;

    }
}
