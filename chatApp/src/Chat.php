<?php
namespace MyApp;
use Ratchet\MessageComponentInterface;
use Ratchet\ConnectionInterface;

class Chat implements MessageComponentInterface {

    private $clients;
	private $id_user;
	private $id_dest;

    public function __construnct() {
        $this->clients = array();
    }

    public function onOpen(ConnectionInterface $conn) {

		$this->id_user=($conn->httpRequest->getUri()->getQuery());

        $this->clients[] = $conn;

        echo "New Connection";
    }

    public function onMessage(ConnectionInterface $from, $msg) {


		$obj = json_decode($msg);
		$this->id_dest=$obj->{'id_dest'};
		

        foreach($this->clients as $client) {
			
			print_r($client->httpRequest->getUri()->getQuery());
			
            if (  ($client != $from)  &&  (strcmp($client->httpRequest->getUri()->getQuery(), $this->id_dest)==0)   ) {
                $client->send($msg);

            }
        }

    }

    public function onClose(ConnectionInterface $conn) {

        echo "Connection closed";

    }

    public function onError(ConnectionInterface $conn, \Exception $e) {
        echo $e->getMessage();
    }
}