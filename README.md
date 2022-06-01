# PRNG-in-Blockchain-based-E-lottery-System
Organization of files:
- Folder name: Paper 1
	- Implementation of "Design of Blockchain Lottery for Thai Government" extension.
	- Files: Lottery_Thai.sol, test_data.txt
- Folder name: Paper 2
	- Implementation of "Lottery DApp from Multi-Randomness Extraction.
	- Files: Lottery_Dapp.sol, test_data.txt
 

Running instructions:
------------------------

Tools Setup:
--------------
1. Install ganache: 
	- Download from: https://github.com/trufflesuite/ganache/releases
	- (for windows:) Download Ganache-2.1.1-win-setup.exe
	- Install and run. 
	- Once ganache GUI is running go to 
		- setting (setting button) -> CHAIN -> set gas limit 10000000
		- setting (setting button) -> ACCOUNTS & KEYS -> set the mnemonic "wine school heavy thought bomb awkward acquire urban bulk aware high true"
		- Restart ganache (by pressing the restrat button on top right)

2. Go to https://remix.ethereum.org/
	- Load the files.
		- For paper 1: Lottery_Thai.sol
		- For paper 2: Lottery_Dapp.sol
	- Set the compiler version to version:0.4.26+commit.4563c3fc
	- For paper 1: select the Lottery_Thai.sol file first. Next, set "lottery name" and press the Start to compile button in remix.
	- Once the compilation is done, switch to Run tab in remix.
	- In the run tab:
		- Environment: Web3 Provider 
			- A pop up will appear, select ok. 
			- Set Web3 Provider Endpoint: http://localhost:7545 (it will connect remix with ganache)
		- Gas limit: set 10000000.
		- Then select the Election contract from the drop down option and press deploy.
		- (For paper 2) Select the Lottery_Dapp.sol file first. Then set "lottery Name" during deployment
	        - Once deployed all the functions of the smart contract will become visible under "Deployed Contracts".
	

Testing: (Paper 1)
---------------------
1. Open the "test_data" file. 
2. Use the provided data in order to test the execution.
3. Copy and paste the data provided for each function to the deployed functiones in remix (inside the Lottery_Thai contract under "Deployed Contracts"). 
4. For lottery buyers use the registerd accounts (by switching the accounts under Run tab in remix).
5. To get the execution cost:
	-  From ganache: Switch to Transactions tab and see "GAS USED" 

Testing: (Paper 2)
---------------------

1. Open the "test_data" file.
2. Use the provided data in order to test the execution.
3. Copy and paste the data provided for each function to the deployed functiones in remix (inside the Lotter_Dapp contract under "Deployed Contracts"). 
4. For lottery buyers use the registerd accounts (by switching the accounts under Run tab in remix). 
5. To get the execution cost:
	-  From ganache: Switch to Transactions tab and see "GAS USED" 
	-  

PRNG: RANDAO Commit-Reveal Scheme
-----------------------------------

Environment: java 9.0.1 above

1. Open terminal or command prompt

2. Compile the code:
	In the folder directory: javac *.java

3.Run the code: 
	In the folder directory: java Test (number of participate)

4. Get the output



NIST Python files are from: https://github.com/GINARTeam/NIST-statistical-test
