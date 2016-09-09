/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_System_Package;

/**
 *
 * @author d00159804
 */

public class Management_System {
   
    String Title;
    String Genre;
    int ID;
    int Rating;
    int Quantity = 0;

    public Management_System(String Title, String Genre, int ID, int Rating, int Quantity) {
        this.Title = Title;
        this.Genre = Genre;
        this.ID = ID;
        this.Rating = Rating;
        this.Quantity = Quantity;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

        public String toString() {
            String out = "Title:" + Title + "\n";
            out += "Genre:" + Genre + "\n";
            out += "ID:" + ID + "\n";
            out += "Rating:" + Rating + "\n";
            out += "Quantity:" + Quantity + "\n\n";
            return out;
        }
    
    
//    @Override
//    public String toString() {
//        return "Management_System{" + "Title=" + Title + ", Genre=" + Genre + ", ID=" + ID + ", Rating=" + Rating + ", Quantity=" + Quantity + '}';
//    }
    
}
