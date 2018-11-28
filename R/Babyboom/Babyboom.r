#!/usr/bin/Rscript

data <- read.csv("./babyboom.csv")

print(summary(data))

plot(x = data$time, y = data$weight,
     xlab = "Time of birth",
     ylab = "Weight at birth",
     main = "Babyboom - Time vs Weight at birth")

boxplot(weight ~ sex, data = data, xlab = "Sex of baby",
        ylab = "Weight of baby (g)", main = "Babyboom - Sex to weight data")

boxplot(minutes ~ sex, data = data, xlab = "Sex of baby",
        ylab = "Time taken to be born (mins)", main = "Babyboom - Sex to time taken data")

relation <- lm(data$weight ~ data$minutes)
sum <- summary(relation)
plot(sum)
# for(name in names(sum)) {
#     print(sum$name)
# }
