package ru.otus.lec13.service.generator;

public interface SequenceGenerator {

    long getNextId(String seqName);

}
