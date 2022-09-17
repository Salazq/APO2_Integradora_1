package model;

public class Score {

    private double score;
    private String name;
    private Score right;
    private Score left;

    public Score (double score, String name){
        this.score=score;
        this.name=name;
        right=null;
        left=null;
    }

    public void tableScore(){
        
        System.out.printf("|%20s| |%10s|", name , String.format("%.2f", score),"\n");
    }

    /** 
     * @return Score
     */
    public Score getRight(){
        
        return right;
    }

    /** 
     * @param right
     */
    public void setRight(Score right){
        
        this.right=right;
    }

    /** 
     * @return Score
     */
    public Score getLeft(){
        
        return left;
    }

    /** 
     * @param left
     */
    public void setLeft(Score left){
        
        this.left=left;
    }

    /** 
     * @return double
     */
    public double getValue(){
        
        return score;
    }

    /** 
     * @param score
     */
    public void setScore(int score){
        
        this.score=score;
    }

    /** 
     * @return String
     */
    public String getName(){
        
        return name;
    }

    /** 
     * @param name
     * @return String
     */
    public String setName(String name){
        return this.name=name;
    }
}
