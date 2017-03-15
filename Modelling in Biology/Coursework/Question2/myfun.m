function [ x ] = myfun( r, t )

x0 = 0.8;
r = r;

x = zeros(1, length(t));
x(1) = x0;
for i = t; %1:1:30
    x(i+1) = r*x(i)*(1-x(i));
end

x(length(t)) = []; %discard the last element to match the lengths

end

