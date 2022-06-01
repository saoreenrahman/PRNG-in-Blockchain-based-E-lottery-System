 
 pragma solidity ^0.4.17;

    contract Lottery {
        address public manager;
        address[] public players;
        address public winner;
        uint256 public seed1;
        uint256 public seed2;
        uint256 public seed3;
         string public name = "Lottery";
         uint256 public randomNumber;
 
    modifier managerOnly(){
    
    require(msg.sender == manager); // only manager can access
 _;
}

    function Lottery(string LotteryName) public payable {

        name = LotteryName;
         manager = msg.sender;

    }
 
    function BuyLottery() public payable {
    require(msg.sender != manager);
    require(msg.value > .01 ether); // some requirements satisfied, then go through next
 players.push(msg.sender);
} 

    function pickWinner() public payable returns(address) { 
   require(msg.sender == manager);
     uint winnerIndex = randomNumber % players.length;
     winner = players[winnerIndex];
   
    players[winnerIndex].transfer(address(this).balance);

    players = new address[](0);
    return winner;

}

    function getPlayers() public view returns (address[]) {
  return players;
 }
 
    function Inputseed1(uint256 _data1) public returns(uint){ 
  //   require(seeds.length==seed);
         require(msg.sender != manager);
        seed1= _data1; 
           
      return seed1; 

    }
    
    function Inputseed2(uint256 _data2) public returns(uint){ 
  //   require(seeds.length==seed);
       require(msg.sender != manager);  
          seed2= _data2;
           
      return seed2; 

    }    

    function Inputseed3(uint256 _data3) public returns(uint){ 
         require(msg.sender != manager);
        seed3= _data3; 
           
      return seed3; 

    }   
    
    function random() public returns (uint) {
  randomNumber = uint64(sha3(sha3(block.blockhash(block.number), seed1,seed2,seed3), now));
 return randomNumber;
}    
 
 }


