import io
import struct

# 给定的字节流

path =  "addNodeTC4.dat"
with open(path, 'rb') as file:

    data = file.read()
    print(len(data))
    print("-----------data is----------\n",data)
    byte_data = data
    # 将字节流解析为一系列整数
    int_list = struct.unpack('>' + 'I' * (len(byte_data) // 4), byte_data)

    # 输出解析出的整数列表
    print("Parsed Integers:", int_list)

    # 假设这些整数表示多项式的系数
    # 比如多项式：4x^7 + 1x^6 + 3x^5 + 2x^4 + 2x^3 + 3x^2 + 1x^1 + 4x^0
    polynomial = " + ".join(f"{coef}x^{i}" for i, coef in enumerate(reversed(int_list)) if coef != 0)

    print("Polynomial:", polynomial)



