Cleanup
figure(1)
hold all

global r k p

p = 0;          %predators 0 = off, 1 = on
r=0.6;
k=4;
for x0=0:1:6;
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ODE45 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [t,z]=ode45(@dxdtfun,[0 10],x0); %numerical method
    plot(t,z)
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% END OF ODE45 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %figure(1);
    %hold all
    %xlabel('t')
    %ylabel('y')
    %legend ('n = 0', 'n = 0.03', 'n = 7')
    %hold off
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    Equ1 = ['Dx=' num2str(r) '*x*(1-x/' num2str(k) ')-' num2str(p) '*x^2/(1+x^2)'];
    Equ2 = ['x(0)=' num2str(x0)];
    A = dsolve(Equ1,Equ2);    %calculate analytical solution
    Equ = char(A);                %make it readable
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
    y = zeros(length(t),1);
    for a=1:length(t)
        y(a) = eval(Equ);
    end
    plot(t,y)
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
end