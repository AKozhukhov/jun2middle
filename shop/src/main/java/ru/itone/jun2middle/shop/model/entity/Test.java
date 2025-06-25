package ru.itone.jun2middle.shop.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "test_gen", sequenceName = "test_test_id_seq", allocationSize = 20)
public class Test {
    @Id
    @GeneratedValue(generator = "test_gen")
    @Column(name = "test_id")
    private Long id;

    private String var1;
    private UUID var2;


    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public UUID getVar2() {
        return var2;
    }

    public void setVar2(UUID var2) {
        this.var2 = var2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
