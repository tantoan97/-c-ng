import tensorflow as tf
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from IPython import get_ipython
ipy = get_ipython()
if ipy is not None:
    ipy.run_line_magic('matplotlib','inline')

file_path = 'weather.csv'
data=pd.read_csv(file_path, delimiter=',',header=0,skipinitialspace=True)
data.head(24)

solar = np.array(data['Solar'])
num_periods = 24
f_horizon = 1
x_data = solar[:(len(solar)-(num_periods*2))]
x_batches = x_data.reshape(-1, num_periods, 1)
y_data = solar[f_horizon:(len(solar)-(num_periods*2))+f_horizon]
y_batches = y_data.reshape(-1, num_periods, 1)
print(y_batches.shape)

def test_data(series, forecast, num):
    testX = solar[-(num + forecast):][:num].reshape(-1, num_periods, 1)
    testY = solar[-(num):].reshape(-1, num_periods, 1)
    return testX, testY
X_test, Y_test = test_data(solar, f_horizon, num_periods*2)
print(X_test.shape)

tf.reset_default_graph()
inputs = 1
rnn_size = 100
output = 1
learning_rate=0.001
dropout_keep_prob = tf.placeholder(tf.float32)

X = tf.placeholder(tf.float32, [None, num_periods, 1])
Y = tf.placeholder(tf.float32, [None, num_periods, 1])

rnn_cells=tf.contrib.rnn.BasicRNNCell(num_units=rnn_size, activation=tf.nn.relu)
rnn_output, states = tf.nn.dynamic_rnn(rnn_cells, X, dtype=tf.float32)


output=tf.reshape(rnn_output, [-1, rnn_size])
logit=tf.layers.dense(output, 1, name="softmax")

outputs=tf.reshape(logit, [-1, num_periods, 1])
print(logit)

loss = tf.reduce_sum(tf.square(outputs - Y))

accuracy = tf.reduce_mean(tf.cast(tf.equal(tf.argmax(logit, 1), tf.cast(Y, tf.int64)), tf.float32))

optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate)
train_step=optimizer.minimize(loss)

init=tf.global_variables_initializer()

epochs = 1000

sess = tf.Session()
init = tf.global_variables_initializer()
sess.run(init)
saver = tf.train.Saver()

for epoch in range(epochs):
    train_dict = {X: x_batches, Y: y_batches, dropout_keep_prob:0.5}
    sess.run(train_step, feed_dict=train_dict)
y_pred=sess.run(outputs, feed_dict={X: X_test})
save_path = saver.save(sess, "models/model2.ckpt")
#print(y_pred)
print(y_pred.shape)
y_predict=y_pred.reshape(-1)
# print len(y_predict)
 # for abc in range(len(y_predict)):
 # if y_predict[1,abc] <0:
	#  y_predict[1,abc]=0
#print(y_predict.shape)
#y_predict=y_predict.reshape(1,48)

for i in range(len(y_predict)):
	abc = max(y_predict[i],0)
	y_predict[i] = abc

output_array = np.array(y_predict)
np.savetxt("my_output_file2.csv", output_array, delimiter=",")
print(y_predict.shape)
print(y_predict)

import csv
csvData = y_predict
with open('predict.csv', 'a') as csvFile:
    writer = csv.writer(csvFile)
    writer.writerow(csvData)
csvFile.close()

fig=plt.figure()
plt.title("Solar", fontsize=14)
plt.plot(pd.Series(np.ravel(Y_test)), "bo", markersize=10, label="Actual")
plt.plot(pd.Series(np.ravel(y_pred)), "r.", markersize=10, label="Forecast")
plt.legend(loc="upper left")
plt.xlabel("Time Periods")
plt.show()
fig.savefig('SolarSS.png', 
            facecolor=fig.get_facecolor(), 
            edgecolor=fig.get_edgecolor(),
            dpi = fig.get_dpi())

with tf.Session() as sess:
  # Restore variables from disk.
    saver.restore(sess, "models/model2.ckpt")
    predict=sess.run(outputs, feed_dict={X: X_test})
#print(predict)

#row = predict
#with open('predict.csv', 'a') as csvFile:
#    writer = csv.writer(csvFile)
#    writer.writerow(row)
#csvFile.close()