close all
x0 = 6;                     %initial condition
k = 3/16;                   %initial condition
h=0.01;
t=0:h:10;
x1=zeros(length(t),1);
x2=zeros(length(t),1);
x1(1) = x0;
o = 0.2;
hold; 

for j=1:20
%   EULER
    for i = 1:1:length(t)-1
        x1(i+1)=x1(i)+h*(-k*x1(i))+o*sqrt(h)*randn;
    end
    x2= x2+x1;
%% ODE45
%   [t,x]=ode45(@dxdtfun,t,x0); %numerical method

    plot(t,x1)
     
end

x2 = x2/j;


%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

A = dsolve('Dx=-K*x','x(0)=x0','K=k');    %calculate analytical solution
B = char(A);                %make it readable
C= findstr(B, '{');         %find the { in the string
D= findstr(B, '}');         %find the }
Equ = B(C(1)+1:D(1)-1)      %Isolate the equation, and use it as a function, but as it is it **might not** work with matricies. So...

C=findstr(Equ, 't');        %have to add (a) for the next "for" statement in to the Equ to EVERY 't'

for c=length(C):-1:1;       %count back from the last t (for however many 't's you have)
    Equ = [Equ 'abc'];      %Extend Equ to prevent overflow
    for b=length(Equ)-3:-1:C(c)+1    %Move everything from the last spot three characters to the rear until you hit t. C(c) = where search found 't'
        Equ(b+3) = Equ(b);
    end
    Equ(C(c)+1) = '(';
    Equ(C(c)+2) = 'a';      % Add (a) - ie: overwrite those spots, because we've moved everything out of the way.
    Equ(C(c)+3) = ')';      % This is to make the next "for" loop execute all the way correctly, as we need to do it for each value of t
end
for a=1:length(t)
y(a) = (eval(Equ));         %Evaluate your built string  ----> basically this is y(a) = 4/exp((3*t(a))/5)
end
y = y.';                    %Transpose the matrix, so that it works with t, and so on.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

MsqERR=mse(y-x2)
%RanMsqERR = mse (y-x1)




hold off