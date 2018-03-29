# a tensorflow program to display k-mean clustering
import numpy as np
import tensorflow as tf

def createSamples(nClusters, nSamplesPerCluster, nFeatures, embiggenFactor, seed):
    np.random.seed(seed)
    slices    = []
    centroids = []
    # Create samples for each cluster
    for i in range(nClusters):
        samples = tf.random_normal((nSamplesPerCluster, nFeatures), mean=0.0, stddev=5.0, dtype=tf.float32, seed=seed, name="cluster_{}".format(i))
        currentCentroid = (np.random.random((1, nFeatures))*embiggenFactor) - (embiggenFactor/2)
        centroids.append(currentCentroid)
        samples += currentCentroid
        slices.append(samples)
    # Create a big "samples" dataset
    samples = tf.concat(slices, 0, name='samples')
    centroids = tf.concat(centroids, 0, name='centroids')
    return centroids, samples

def plotClusters(allSamples, centroids, nSamplesPerCluster):
    import matplotlib.pyplot as plt
    # Plot out different clusters
    # Choose a different colour for each cluster
    colour = plt.cm.rainbow(np.linspace(0,1,len(centroids)))
    for i, centroid in enumerate(centroids):
        # Grab just the samples for the given cluster and plot them out with a new colour
        samples = allSamples[i*nSamplesPerCluster:(i+1)*nSamplesPerCluster]
        plt.scatter(samples[:,0], samples[:,1], c=colour[i])
        # Also plot centroid
        plt.plot(centroid[0], centroid[1], markersize=35, marker="x", color='m', mew=10)
        plt.plot(centroid[0], centroid[1], markersize=30, marker="x", color='m', mew=5)
        plt.show()
