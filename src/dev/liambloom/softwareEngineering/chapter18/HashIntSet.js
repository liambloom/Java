export class HashIntSet {
    static #MAX_LOAD_FACTOR = 0.75;
    #elementData;
    #size;

    constructor() {
        this.#elementData = new Array(10).fill(new HashEntry());
        this.#size = 0;
    }

    #hashFunction(value) {
        return Math.abs(value) % this.#elementData.length;
    }

    contains(value) {
        const bucket = hashFunction(value);
        let current = elementData[bucket];

        while (current !== null) {
            if (current.data === value)
                return true;
            current = current.next;
        }
        return false;
    }

    #loadFactor() {
        return this.size / this.elementData.length;
    }

    add(value) {
        if (!this.contains(value)) {
            if (this.loadFactor() >= HashIntSet.#MAX_LOAD_FACTOR) 
                this.#rehash();
            
                const bucket = hashFunction(value);
                this.#elementData[bucket] = new HashEntry(value, this.#elementData[bucket]);
                this.#size++;
        }
    }

    
}

class HashEntry {

}