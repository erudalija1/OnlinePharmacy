package ba.unsa.etf.onlinepharmacy.Model;

import javax.persistence.*;

@Entity
@Table(name = "review")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String comment;
    private Integer stars;

    public Review() {
    }

    public Review(String comment, Integer stars) {
        this.comment = comment;
        this.stars = stars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
