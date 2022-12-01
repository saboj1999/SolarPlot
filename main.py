import matplotlib.pyplot as plt
import numpy as np


def read_body_data(filename):
    file = open(filename)
    x_data = []
    y_data = []
    for line in file:
        x, y = line.strip().split(":")
        x_data.append(float(x))
        y_data.append(float(y))

    return x_data, y_data


def main():
    # file = open("data.txt")
    # potential_data = []
    # y = .1
    # y_data = []
    # for line in file:
    #     potential_data.append(float(line))
    #     y_data.append(y)
    #     y += .1

    EarthX, EarthY = read_body_data("EarthPosition.txt")
    SunX, SunY = read_body_data("SunPosition.txt")
    MoonX, MoonY = read_body_data("MoonPosition.txt")
    VenusX, VenusY = read_body_data("VenusPosition.txt")
    MarsX, MarsY = read_body_data("MarsPosition.txt")
    PhobosX, PhobosY = read_body_data("PhobosPosition.txt")
    DeimosX, DeimosY = read_body_data("DeimosPosition.txt")

    plt.plot(EarthX, EarthY, color="blue", label="Earth")
    plt.scatter(SunX, SunY, color="yellow", label="Sun")
    plt.plot(MoonX, MoonY, color="grey", label="Moon")
    plt.plot(VenusX, VenusY, color="orange", label="Venus")
    plt.plot(MarsX, MarsY, color="red", label="Mars")
    plt.plot(PhobosX, PhobosY, color="purple", label="Phobos")
    # plt.plot(DeimosX, DeimosY, color="pink", label="Deimos")

    plt.rcParams["figure.figsize"] = (20, 8)
    plt.title("Solar System")
    plt.xlabel("X Position")
    plt.ylabel("Y Position")
    plt.axis('square')
    plt.legend(loc=2, prop={'size': 6})
    plt.show()

    EarthVX, EarthVY = read_body_data("EarthVelocity.txt")
    SunVX, SunVY = read_body_data("SunVelocity.txt")
    MoonVX, MoonVY = read_body_data("MoonVelocity.txt")
    VenusVX, VenusVY = read_body_data("VenusVelocity.txt")
    MarsVX, MarsVY = read_body_data("MarsVelocity.txt")
    PhobosVX, PhobosVY = read_body_data("PhobosVelocity.txt")
    DeimosVX, DeimosVY = read_body_data("DeimosVelocity.txt")

    plt.plot(EarthVX, EarthVY, color="blue", label="Earth")
    plt.scatter(SunVX, SunVY, color="yellow", label="Sun")
    plt.plot(MoonVX, MoonVY, color="grey", label="Moon")
    plt.plot(VenusVX, VenusVY, color="orange", label="Venus")
    plt.scatter(MarsVX, MarsVY, color="red", label="Mars")
    plt.plot(PhobosVX, PhobosVY, color="purple", label="Phobos")
    # plt.plot(DeimosVX, DeimosVY, color="pink", label="Deimos")

    plt.title("Solar System")
    plt.xlabel("X Velocity")
    plt.ylabel("Y Velocity")
    plt.axis('square')
    plt.legend(loc=2, prop={'size': 6})
    plt.show()


if __name__ == '__main__':
    main()
