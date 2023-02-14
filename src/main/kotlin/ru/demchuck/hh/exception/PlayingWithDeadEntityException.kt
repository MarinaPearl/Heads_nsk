package ru.demchuck.hh.exception

class PlayingWithDeadEntityException : IllegalStateException("attempt to play with a dead entity") {}