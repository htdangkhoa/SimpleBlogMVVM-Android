import ne from 'nedb'
import aes256 from 'aes256'

const key = 'DggBBA4DDQAGBAIGBAUDCQ'

const db = new ne({ 
    filename: './post.db', 
    autoload: true,
    timestampData: true,
    afterSerialization: v => {
        return aes256.encrypt(key, v)
    },
    beforeDeserialization: v => {
        return aes256.decrypt(key, v)
    } 
})

export default db