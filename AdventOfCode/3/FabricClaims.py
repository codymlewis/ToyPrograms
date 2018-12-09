#!/usr/bin/env python3
import sys


def part1(filename):
    fabric = [["." for _ in range(1000)] for _ in range(1000)]
    overlaps = 0
    with open(filename) as f:
        for line in f:
            claim_id = str(line[line.find("#") + 1 : line.find("@") - 1])
            left = int(line[line.find("@") + 1 : line.find(",")])
            top = int(line[line.find(",") + 1 : line.find(":")])
            x_dim = int(line[line.find(":") + 1 : line.find("x")])
            y_dim = int(line[line.find("x") + 1 :])
            for dx in range(x_dim):
                for dy in range(y_dim):
                    if fabric[left + dx][top + dy] == "X":
                        continue
                    elif fabric[left + dx][top + dy] != ".":
                        overlaps += 1
                        fabric[left + dx][top + dy] = "X"
                    else:
                        fabric[left + dx][top + dy] = claim_id
    return overlaps


def part2(filename):
    fabric = [["." for _ in range(1000)] for _ in range(1000)]
    claims_set = set()
    with open(filename) as f:
        for line in f:
            overlaps = False
            claim_id = str(line[line.find("#") + 1 : line.find("@") - 1])
            claims_set = claims_set.union({claim_id})
            left = int(line[line.find("@") + 1 : line.find(",")])
            top = int(line[line.find(",") + 1 : line.find(":")])
            x_dim = int(line[line.find(":") + 1 : line.find("x")])
            y_dim = int(line[line.find("x") + 1 :])
            for dx in range(x_dim):
                for dy in range(y_dim):
                    if fabric[left + dx][top + dy] == "X":
                        claims_set = claims_set.difference({claim_id})
                    elif fabric[left + dx][top + dy] != ".":
                        claims_set = claims_set.difference({claim_id, fabric[left + dx][top + dy]})
                        fabric[left + dx][top + dy] = "X"
                    else:
                        fabric[left + dx][top + dy] = claim_id
        return str(claims_set)[2:-2]


if __name__ == "__main__":
    if len(sys.argv) > 1:
        print(f"{part1(sys.argv[1])} inches of the claims overlap")
        print(f"#{part2(sys.argv[1])} does not overlap")
    else:
        print("Please specify a file to read from")
