# SSH Key(Secure Shell)
- access credential that is used in the SSH protocol 
- comes in many sizes, but a popular choice is an RSA 2048-bit encryption, which is comparable to a 617 digit long password.
- always comes in pairs, and every pair is made up of a private key and a public key.
- Who or what possesses these keys determines the type of SSH key pair. 
- If the private key and the public key remain with the user, this set of SSH keys is referred to as user keys.

**Types of keys:** 
1. HOST KEYS - If the private and public keys are on a remote system, then this key pair is referred to as host keys
    - private key - the private key remains on the system being used to access the remote system (i.e. the user’s desktop or laptop) and is used to decrypt information that is exchanged in the SSH protocol.Private keys should never be shared with anyone and should be secured on a system(can be secured by password)
    - public key - A public key is used to encrypt information, can be shared, and is used by the user and the remote server. On the server end, the public key is saved in a file that contains a list of authorized public keys. On the user’s side, the public SSH key is stored in an SSH key management software or in a file on their computer.  
    
2. SESSION KEYS - When a large amount of data is being transmitted, session keys are used to encrypt this information.  


### Creating SSH on mac 
In terminal write:
```
 ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
```
- this command will create a new SSH key using the email as a label
- default directory in which will keys be generated: ```/userHome/.ssh``` as files:
     1. ```id_ed25519``` - file contains private key
     2. ```id_ed25519.pub``` - file contracting public key
### Adding SSH key to gitHub profile

1. Using cd command go to directory containing public SSH key(id_ed25519.pub)
    ```
    cd .ssh 
    ```

    If lost u can:
    - go back to home directory using 
        ```
        cd
        ```
 
    -  go to parent directory using 
        ```
        cd ..
        ```
2. Using text editor tool open public SSH key file(id_ed25519.pub)
    ```
    nano id_ed25519.pub
    ```
 - to exit nano press without saving ```Ctrl+X```
3. Put SSH key to Github
 - profile -> settings -> SSH and GDP key -> SSH key -> Add