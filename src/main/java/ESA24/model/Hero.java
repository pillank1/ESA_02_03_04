package ESA24.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "_hero")
public class Hero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id")
    private UUID id;
    @Column(name = "namehero")
    private String nameHero;
    @Column(name = "level")
    private Integer level;
    @Column(name = "classhero")
    private String classHero;
    @Column(name = "spellcells")
    private Integer spellCells;

    @ManyToOne
    @JoinColumn
    private Player player;

    public Hero() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameHero() {
        return nameHero;
    }

    public void setNameHero(String nameHero) {
        this.nameHero = nameHero;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getClassHero() {
        return classHero;
    }

    public void setClassHero(String classHero) {
        this.classHero = classHero;
    }

    public Integer getSpellCells() {
        return spellCells;
    }

    public void setSpellCells(Integer spellCells) {
        this.spellCells = spellCells;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
