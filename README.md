![GitHub top language](https://img.shields.io/github/languages/top/Vonvikken/Jellyfish)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/Vonvikken/Jellyfish)
[![LICENSE](https://img.shields.io/github/license/Vonvikken/Jellyfish.svg)](https://github.com/Vonvikken/Jellyfish/blob/master/LICENSE)

# Jellyfish animation
A simple JavaFX application that shows an ellipse moving with a wobbling animation, just like a jellyfish seen from
 above!

## Intro
The axes of the ellipse are animated using the following formulas:

<a href="https://www.codecogs.com/eqnedit.php?latex=r_x&space;=&space;R\cdot\sin(t)\cdot\sin\left(\omega&space;t&space;&plus;\frac{\pi}{2}\right)" target="_blank"><img src="https://latex.codecogs.com/svg.latex?r_x&space;=&space;R\cdot\sin(t)\cdot\sin\left(\omega&space;t&space;&plus;\frac{\pi}{2}\right)" title="r_x = R\cdot\sin(t)\cdot\sin\left(\omega t +\frac{\pi}{2}\right)" /></a>
<br/>
<a href="https://www.codecogs.com/eqnedit.php?latex=r_y&space;=&space;R\cdot\sin(t)\cdot\sin\left(\omega&space;t\right)" target="_blank"><img src="https://latex.codecogs.com/svg.latex?r_y&space;=&space;R\cdot\sin(t)\cdot\sin\left(\omega&space;t\right)" title="r_y = R\cdot\sin(t)\cdot\sin\left(\omega t\right)" /></a>

whereas the center moves randomly.
<br/>

![](Jellyfish.gif)

## Requirements
* Java 13 (but can be run since Java 11)
* Maven

## Compile
`mvn compile`

## Run
`mvn javafx:run`

## TODOs
* Configurable parameters
