# finds the rolling average of the numpy randint
import numpy as np
import tensorflow as tf

x = tf.Variable(0, name='x')
avg = tf.Variable(0, name='avg')
#ran = tf.constant(1000, name='ran')
ran = 10000
model = tf.global_variables_initializer()

with tf.Session() as session:
    session.run(model)
    for i in range(ran):
        x = np.random.randint(1000)
        avg = avg + x

    avg = avg / ran
    print(session.run(avg))

