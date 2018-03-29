# demonstrates the various occurances with a 3d matrix
import tensorflow as tf

a = tf.constant([[[1,2,3],[4,5,6]],[[7,8,9],[10,11,12]]], name='a')
b = tf.constant(100, name='b') # scalar
c = tf.constant([101,102,103], name='c') # array
d = tf.constant([[1001,1002,1003],[1004,1005,1006]]) # matrix
addOp  = a + b
addOp2 = a + c
addOp3 = a + d
print(a.shape)
print(d.shape)

with tf.Session() as session:
    print("Scalar broadcast:",session.run(addOp))
    print("Array broadcast:",session.run(addOp2))
    print("Matrix broadcast:",session.run(addOp3))
