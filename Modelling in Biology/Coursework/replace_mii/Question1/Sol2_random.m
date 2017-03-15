Cleanup
figure(1)
hold all
%figure(2)
%hold all

global r k p

p = 1;          %predators 0 = off, 1 = on
%r=0.6;
k=13;
for a = 1:1:10
    r = a * 1 / 10;
    for b = 1:1:40
        x = b*0.25;
        h(b,a) = (r*(1-x/k));
        g(b,a) = x/(1+x^2);
    end
end
    
figure(1)
surf(h)
surf (g)
xlabel('r multiplied by 10')
ylabel('x multiplied by 0.4')
zlabel('magnitude (h or g)')