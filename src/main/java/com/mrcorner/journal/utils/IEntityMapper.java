package com.mrcorner.journal.utils;

public interface IEntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}
