package com.company;

public class CharStat implements Comparable{
    Character value;
    int amount;


    public CharStat(Character val){
        value = val;
        amount = 1;
    }

    public void increase(){
        amount++;
    }

    @Override
    public int compareTo(Object other){
        CharStat cs = (CharStat) other;
        int result = amount - cs.amount;
        if(result != 0){
            return result / Math.abs(result);
        }
        return result;
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if (other == null || other.getClass() != getClass()){
            return false;
        }

        CharStat cs = (CharStat) other;
        if(cs.value.equals('—')){
            ((CharStat) other).increase();
            return true;
        }
        if(value == cs.value){
            ((CharStat) other).increase();
            return true;
        }
        return false;

    }


    @Override
    public int hashCode(){
        return (int)value;
    }
}
