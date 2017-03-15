x0 = 4;
k = 3/5;
[t,x]=ode45(@(t,x)-k*x,[0 10],x0);
plot(t,x)
%figure;
%A = dsolve('Dx=-k*x','x(0)=4','k=3/5');
y = 4*exp(-k*t);
hold;
plot(t,y)
mean_square_error = mse(y-x)