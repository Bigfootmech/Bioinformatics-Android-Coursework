function [ dxdt ] = dxdtfun( t,x )

h = 0.01;
o=0.2;
k = 3/16;

dxdt = -k*x + o*randn/sqrt(h);




end

