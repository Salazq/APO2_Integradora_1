package model;

public class Score {

    private int score;
    private String name;
    private Score right;
    private Score left;

    public Score (int score, String name){
        this.score=score;
        this.name=name;
        right=null;
        left=null;
    }

    public String toString(){
        
        return  score + "          "+ name;
    }

    public Score getRight(){
        
        return right;
    }

    public void setRight(Score right){
        
        this.right=right;
    }

    public Score getLeft(){
        
        return left;
    }

    public void setLeft(Score left){
        
        this.left=left;
    }

    public int getValue(){
        
        return score;
    }

    public void setScore(int score){
        
        this.score=score;
    }


    public String getName(){
        
        return name;
    }

    public String setName(String name){
        return this.name=name;
    }

}
