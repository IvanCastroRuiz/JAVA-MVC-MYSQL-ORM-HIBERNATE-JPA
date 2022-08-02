package com.mycompany.ejemplohibernate.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-07-31T21:12:45", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Song.class)
public class Song_ { 

    public static volatile SingularAttribute<Song, String> songName;
    public static volatile SingularAttribute<Song, String> artist;
    public static volatile SingularAttribute<Song, Integer> id;

}