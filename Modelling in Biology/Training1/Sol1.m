x0 = 4;
k = 3/5;
[t,x]=ode45(@(t,x)-k*x,[0 10],x0);
plot(t,x)