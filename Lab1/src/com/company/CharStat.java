package com.company;

public class CharStat implements Comparable{
    Character Value;
    int Amount;


    public CharStat(Character val){
        Value = val;
        Amount = 1;
    }

    public void increase(){
        Amount++;
    }

    @Override
    public int compareTo(Object other){
        CharStat cs = (CharStat) other;
        int result = Amount - cs.Amount;
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
        if(cs.Value.equals('—')){
            ((CharStat) other).increase();
            return true;
        }
        if(Value == cs.Value){
            ((CharStat) other).increase();
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (int)Value;
    }
}
