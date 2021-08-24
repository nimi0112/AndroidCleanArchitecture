# Welcome to # Android clean architecture!

An app which fetches the Github repos of a user based on his username. The goal of this project is to show the principles of clean architecture using Repository, UseCases and writing unit test cases for them.


# Layer's



## **Presentation Layer**

Includes **Activities**, **Fragments** and **ViewModels**.


## Domain Layer

Contains all the **use cases** of the application.

## Model Layer

The model layer has all the **repositories** that the domain layer can use.

# Test Cases

I have written Test cases for ViewModel and Usecases.

## RepoListVMUnitTest

Contains Test cases for the View Model

## RepoListUseCasesUnitTest

Contains Test cases for the UseCases