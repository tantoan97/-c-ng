import tensorflow as tf
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from IPython import get_ipython
ipy = get_ipython()
if ipy is not None:
    ipy.run_line_magic('matplotlib','inline')

exam=[[[[1,1,1],[178,62,74]]]]
x=np.array(exam)
print(x.shape)