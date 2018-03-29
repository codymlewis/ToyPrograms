# demonstrates various linear algebra operations with tensorflow
import tensorflow as tf

a = tf.constant([1,2,3], name='a') # lists
b = tf.constant([4,5,6], name='b')
c = tf.constant(4, name='c') # scalar
addOp = a + b # column + column
addOp2 = a + c # list + scalars



with tf.Session() as session:
    print("Elementwise operation:",session.run(addOp))
    print("Broadcasted operation:",session.run(addOp2))
