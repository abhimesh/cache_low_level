import org.w3c.dom.Node;

import java.util.HashMap;

public class cache {
    private final evictionPolicy policy;
    private final storage storage;
    cache(evictionPolicy policy , storage storage)
    {
        this.policy=policy;
        this.storage=storage;
    }
    public void set(String key,String value)
    {
        this.storage.set(key,value);
    }
    public String get(String key)
    {
        return this.storage.get(key);
    }
}
class Nodes{
    String data;
    Nodes prev;
    Nodes next;
    public Nodes(String data)
    {
        this.data=data;
        this.prev=null;
        this.next=null;
    }
}
class evictionPolicy{
    Nodes head,tail=null;
    String remove()
    {
        if(tail==null)
        {System.out.println("no nodes to remove");}
        Nodes last_node=tail;
        String ans=last_node.data;
        tail=last_node.prev;
        tail.next=null;
        last_node.prev=null;
        last_node.next=null;
        return ans;
    }
    Nodes Remove_any_node(Nodes node)
    {
        if(node==head || node==tail)
        {
            if(node==tail&&node!=head)
            {
                node.prev.next=null;
                node.prev=null;
            }
        }
        else{
        node.next.prev=node.prev;
        node.prev.next=node.next;
        node.prev=null;
        node.next=null;}
        return node;
    }
    Nodes add(String key)
    {
        Nodes new_nodes=new Nodes(key);
        if(head==null && tail==null)
        {
            head=new_nodes;
            tail=new_nodes;
        }
        else {
            Nodes current_new_node=head;
            head=new_nodes;
            head.next=current_new_node;
            current_new_node.prev=head;
        }
        return new_nodes;
    }


}
class storage {
    HashMap<String,String> maps;
    HashMap<String,Nodes> maps_of_nodes;
    int size;
    evictionPolicy object;
    storage(int size)
    {
        this.size=size;
        maps=new HashMap<String,String>();
        this.object =new evictionPolicy();
        maps_of_nodes=new HashMap<String,Nodes>();
    }

    public String get(String key)
    {

        if(maps.containsKey(key))
        {

            Nodes temp=object.Remove_any_node(maps_of_nodes.get(key));
            object.add(key);
            return maps.get(key);


        }
        else {
            System.out.println("key is not present in cache");
            return "";
        }
    }
    public void set(String key , String value)
    {

        if(maps.size()<size){
            maps.put(key,value);
            Nodes node=object.add(key);
            maps_of_nodes.put(key,node);
        }
        else {
            String evict=object.remove();
            maps.remove(evict);
            maps.put(key, value);
            Nodes node=object.add(key);
            maps_of_nodes.put(key,node);
        }
    }
}
