pragma solidity ^0.4.17;

contract Lottery {
 address public manager;
 address[] public players;
 address public winner;
 uint256 public data;
 string public name = "Lottery";
 
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
     uint winnerIndex = data % players.length;
     winner = players[winnerIndex];
    
players[winnerIndex].transfer(address(this).balance);
 // reset the players array and (0) is initial size
 players = new address[](0);
return winner;

}
 
function inputRandomNumber(uint256 _data) public{ 
       require(msg.sender == manager);
        data=_data;
        
    }
    
function getPlayers() public view returns (address[]) {
  return players;
// return winnerIndex;
 }

}
