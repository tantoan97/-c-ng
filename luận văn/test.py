import tensorflow as tf
import csv
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from IPython import get_ipython
ipy = get_ipython()
if ipy is not None:
    ipy.run_line_magic('matplotlib','inline')

file_path = 'test1.csv'
data=pd.read_csv(file_path, delimiter=',',header=0,skipinitialspace=True)
data.head(24)

abc = np.array(data['abc'])
num_periods = 5
f_horizon = 1
soluong=len(abc)
print(soluong)
soluong=(soluong//num_periods)
print(soluong)
print(abc)
xyz=abc[:soluong*num_periods]
xyz=xyz.reshape(num_periods,-1)
xyz=xyz.transpose()
print(xyz.shape)
print(xyz)

# x_data = temperature[:(len(temperature)-(num_periods*2))]
# x_batches = x_data.reshape(-1, num_periods, 1)
# y_data = temperature[1:(len(temperature)-(num_periods*2))+f_horizon]
# y_batches = y_data.reshape(-1, num_periods, 1)
# print(y_batches.shape)

# print(y_pred.shape)
# y_predict=y_pred.reshape(-1)
# print(y_predict.shape)
# print(y_predict)

csvData = xyz
with open('test2.csv', 'w') as csvFile:
    writer = csv.writer(csvFile)
    writer.writerows(csvData)
csvFile.close()