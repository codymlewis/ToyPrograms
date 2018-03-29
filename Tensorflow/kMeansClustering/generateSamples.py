# generates samples for the k-means clustering
import numpy as np
import tensorflow as tf

from functions import createSamples
from functions import plotClusters

nFeatures = 2
nClusters = 3
nSamplesPerCluster = 500
seed = 700
embiggenFactor = 70

np.random.seed(seed)

centroids, samples = createSamples(nClusters, nSamplesPerCluster, nFeatures, embiggenFactor, seed)

model = tf.global_variables_initializer()
with tf.Session() as session:
    sampleValues   = session.run(samples)
    centroidValues = session.run(centroids)
plotClusters(sampleValues, centroidValues, nSamplesPerCluster)
