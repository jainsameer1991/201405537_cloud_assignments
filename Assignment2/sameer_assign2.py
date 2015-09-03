import sys
from mininet.net import Mininet
from mininet.node import Controller
from mininet.cli import CLI
from mininet.log import setLogLevel, info
from mininet.link import TCLink
def emptyNet():
    no_hosts=int(sys.argv[1])
    no_switches=int(sys.argv[2])
    host_1 = "12.0.0."
    host_2 = "11.0.0."
    "Create an empty network and add nodes to it."

    net = Mininet( controller=Controller )

    info( '*** Adding controller\n' )
    net.addController( 'c0' )

    info( '*** Adding hosts\n' )
    h=[]
    j=1

    for i in range(no_hosts):
        if(i % 2 != 0):
            ipallocate=host_2+str(j);
            hostName = "h"+str(j)
            h.append(net.addHost(hostName,ip=ipallocate))
            j=j+1
        else:
            ipallocate=host_1+str(j)
            hostName = "h"+str(j)
            h.append(net.addHost(hostName,ip=ipallocate))
            j=j+1
    print h
    

    info( '*** Adding switch\n' )
    switch_list=[]
    k=1
    j=0
    
    for i in range(no_switches):

        switch_list.append(net.addSwitch( 's'+str(k) ))
        k+=1
        info( '*** Creating links\n' )
        tmp=net.addLink( h[j], switch_list[i] )
        j+=1
        tmp.intf1.config(bw=1)
        tmp=net.addLink( h[j], switch_list[i]  )
        tmp.intf1.config(bw=2)
        j+=1

    list_len=len(switch_list)
    for i in range(list_len-1):
        net.addLink(switch_list[i],switch_list[i+1])


    info( '*** Starting network\n')
    net.start()

    info( '*** Running CLI\n' )
    CLI( net )

    info( '*** Stopping network' )
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    emptyNet()
