package org.launchcode.hospitaladministration.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Event)) return false;
//        AbstractEntity entity = (AbstractEntity) o;
//        return id == entity.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

}