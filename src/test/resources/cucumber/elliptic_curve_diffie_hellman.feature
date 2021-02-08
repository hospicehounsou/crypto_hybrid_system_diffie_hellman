Feature: Elliptic Curve and Diffie Hellman

  Scenario: KeyPair not exists
    Given It's the first launch of the app
    And I check if  the mobile privateKey is empty
    And I also check if the mobile publicKey is empty
    When I generate the mobile Keypair
    Then I store the mobile privatekey as string in local storage
    Then I also store the mobile publicKey as string in local storage

  Scenario: KeyPair exists
    Given It's not  the first launch of the app
    And I check if  the mobile privateKey  is not empty
    And  I also check if the mobile publickey is not empty
    Then  I do nothing

  Scenario Outline: Encrypt plain data
    Given The mobile already generates Keypair  and the mobile knows the server public key
    When  I Encrypt plain data "<plainData>" by using the diffie hellman shared key. The shared key is combination of mobile private key and the server public key
    Then  It success with the cipher "<encryptedData>" as output
    Examples:
      | plainData | encryptedData            |
      | Yoyoy     | sQezmSLKaYeak5fKF2xJSA== |


  Scenario Outline: Decryt ciper
    Given The mobile already generates Keypair  and the mobile knows the server public key.
    When I decrypt data cipher "<encryptedData>" by using the diffie hellman shared key. The shared key is combination of mobile private key and the server public key
    Then It success with the plain data "<plainData>" as output
    Examples:
      | encryptedData            | plainData |
      | sQezmSLKaYeak5fKF2xJSA== | Yoyoy     |








