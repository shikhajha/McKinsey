package edu.stvns.project.mckensie;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

/*
 * This programs currently uses JOptionPane to get user input of int array but it can be extended to
 * get input from any source.
 * Upon receiving the input it creates a map of integer and its occurence. This map is sorted before displaying the result
 */
public class IntegerOccurenceCounterMain {
    private static final String DEMILITER = ",";

    private void start() {
        int[] intArray = getIntArrayFromUserInput();

        Map<Integer,Integer> intMap = new HashMap<Integer,Integer>();
        for (int i : intArray) {
            int occurence = 1;
            if (intMap.containsKey(i)) {
                occurence = intMap.get(i);
                occurence +=1;
            }
            intMap.put(i, occurence);
        }
        displayResults(intMap);
    }

    /*
     * Convert the map to sorted List. Sorting is done by Occurence of integer in the map
     */
    private List<IntElement> getSortedList(Map<Integer,Integer> intMap)
    {

        List<IntElement> list = new ArrayList<IntElement>();

        for(int i : intMap.keySet())
        {
            list.add(new IntElement(i,intMap.get(i)));
        }

        Collections.sort(list, new OccurenceComparator());
        return list;
    }

    private void displayResults(Map<Integer,Integer> intMap) {
        //first sort the map according to occurence
        List<IntElement> sortedList = getSortedList(intMap);
        StringBuilder builder = new StringBuilder();
        for(IntElement i: sortedList)
        {
            builder.append(i.getValue()+" occurs "+ i.getOccurence()+" times.\n");
        }
        System.out.println(builder.toString());
        JOptionPane.showMessageDialog(null, builder.toString());
    }

    /*
     * Shows dialog for user input. Parses the input and returns int array
     */
    private int[] getIntArrayFromUserInput() {
        String input = null;
        String invalidInputString = "";

        boolean validInput = false;
        while (!validInput) {
            input = JOptionPane.showInputDialog(invalidInputString + "Enter " + DEMILITER
                    + " separated integers(Spaces will be trimmed)");
            if (input == null || input.isEmpty() || !input.contains(DEMILITER)) {
                invalidInputString = "Invalid input!\n";
                continue;
            }
            validInput = true;
        }
        String[] intStrs = input.split(DEMILITER);
        int[] intArray = new int[intStrs.length];
        for (int index = 0; index < intStrs.length; index++) {
            String str = intStrs[index];
            if (str.isEmpty()) {
                continue;
            }
            intArray[index] = Integer.valueOf(intStrs[index]);
        }
        return intArray;

    }

    public static void main(String[] args) {
        new IntegerOccurenceCounterMain().start();

    }

    private static class IntElement{
        private int value;
        private int occurence;

        public IntElement(int value, int occurence) {
            super();
            this.value = value;
            this.occurence = occurence;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + value;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            IntElement other = (IntElement) obj;
            if (value != other.value)
                return false;
            return true;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getOccurence() {
            return occurence;
        }

        public void setOccurence(int occurence) {
            this.occurence = occurence;
        }

    }

    private static class OccurenceComparator implements Comparator<IntElement>
    {

        @Override
        public int compare(IntElement o1, IntElement o2) {
            if (o1.occurence < o2.occurence) {
                return 1;
            } else if (o1.occurence > o2.occurence) {
                return -1;
            }
            else if(o1.value < o2.value){
                return -1;
            }
            else if(o1.value > o2.value)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

    }

}