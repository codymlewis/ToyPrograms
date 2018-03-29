# demonstrates various linear algebra operations involving matrices 
# with tensorflow
import tensorflow as tf
 
a = tf.constant([[1,2,3], [4,5,6]], name='a') # matrices
b = tf.constant([[1,2,3], [4,5,6]], name='b')
d = tf.constant([100,101,102], name='d')
c = tf.constant(100, name='c') # scalar
addOp = a + b # column + column
addOp2 = a + c # list + scalars
addOp3 = a + d # 1d matrix broadcasted into 2d matrix
 
with tf.Session() as session:
    print("Elementwise operation:",session.run(addOp))
    print("Broadcasted operation:",session.run(addOp2))
    print("Matrix broadcast:",session.run(addOp3))
