package es.msanchez.templates.java.spring.builder.generic;

public interface Randoms {

    Long randomLong();

    Long randomPositiveLong();

    Integer randomInteger();

    Integer randomPositiveInteger();

    Integer randomPositiveInteger(final Integer limit);

    String randomAlphanumeric();

    String randomAlphabetic();

    Boolean randomBoolean();

}
