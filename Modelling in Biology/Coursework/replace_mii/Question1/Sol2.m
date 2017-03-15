Cleanup
figure(1)
hold all
%figure(2)
%hold all

global r k p

p = 1;          %predators 0 = off, 1 = on
k=13;

if k<10
    x = 0:0.25:10;
else
    x = 0:0.25:k;
end
g = x./(1+x.^2);
plot(x, g)
xlabel('x')
ylabel('mag of g or h')

for r=[0.25 0.5 0.8];
    h = (r*(1-x/k));
    plot(x, h)
end