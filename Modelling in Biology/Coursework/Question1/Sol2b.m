Cleanup
figure(1)
hold all

global r k p

p = 1;          %predators 0 = off, 1 = on
k=4;

if k<10
    x = 0:0.25:10;
else
    x = 0:0.25:k;
end
g = x./(1+x.^2);
plot(x, g)
xlabel('x')
ylabel('mag of g or h')
        str = ['Nullclines vs x'];
        title(str)
leg = cellstr('g');

count = 1;
for r=[0.6];
    h = (r*(1-x/k));
    plot(x, h)
    leg = [leg ; 'h where r = ' num2str(r)];
    
    vars = ['r=' num2str(r) ', k = ' num2str(k)];
    X = solve('0=(r*(1-(x/k))) -(x/(1+x^2))', vars);
    Lee = (X.x);
    for i = 1:1:length(X.x)
        if (double(abs(imag(X.x(i)))) < 0.01)
            FixPts(i,count) = fsolve(@(x) ((r*(1-(x./k))) -(x./(1+x.^2))),double(real(X.x(i))));
        end
    end
    count = count + 1;
end
FixPts

legend (leg)
