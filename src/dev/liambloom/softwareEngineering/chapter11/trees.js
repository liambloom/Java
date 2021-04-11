/* Mr Marques tells us to make our own independent projects, and he said when I
    asked that they could be in another language as long as they were related to
    what we were doing in class. TreeSets and TreeMaps don't exist in javascript,
    so I thought I'd add them */
// jshint esversion:6, -W097
/* globals console */

"use strict";

const secret = new WeakMap();
function defaultCompare(a, b) {
    a = a.toString();
    b = b.toString();
    if (a == b)
        return 0;
    else if (a < b)
        return -1;
    else
        return 1;
}

export class TreeSet {
    constructor(compareFunction = defaultCompare) {
        if (typeof compareFunction !== "function") 
            throw new TypeError();
        secret.put(this, { compareFunction });
    }

    add(...values) {
        if (values.length !== 1) {
            for (let value of values)
                this.add(value);
        }
        else {
            const value = values[0];
            const priv = secret.get(this);
            if (priv.root === undefined)
                priv.root = new TreeNode(this.compareFunction, value);
            else
                priv.root.add(value);
        }
    }

    contains(value) {
        const { root } = secret.get(this);
        return root === undefined ? false : root.contains(value);
    }

    remove(...values) {
        if (values.length !== 1) {
            for (let value of values)
                this.remove(value);
        }
        else {
            const value = values[0];
            const priv = secret.get(this);
            if (priv.root !== undefined) {
                if (this.compareFunction(priv.root, value))
                    priv.root = undefined;
                else
                    priv.root.remove(value);
            }
        }
    }

    get compareFunction() {
        return secret.get(this).compareFunction;
    }
}

class TreeNode {
    constructor(compareFunction, element) {
        this.compareFunction = compareFunction;
        this.element = element;
        this.lessDepth = 0;
        this.greaterDepth = 0;
    }

    treeNames(value) {
        const comp = this.compareFunction(value, this.element);
        if (comp < 0) {
            return {
                subTree: "less",
                subTreeCounter: "lessDepth"
            };
        }
        else if (comp > 0) {
            return {
                subTree: "greater",
                subTreeCounter: "greaterDepth"
            };
        }
    }

    add(value) {
        const { subTree, subTreeCounter } = this.treeNames(value);

        if (this[subTree] === undefined) {
            this[subTree] = new TreeNode(this.compareFunction, value);
            this[subTreeCounter]++;
            return true;
        }
        else {
            const added = this[subTree].add(value);
            if (added)
                this[subTreeCounter]++;
            return added;
        }
    }

    contains(value) {
        const { subTree } = this.treeNames(value);

        if (this[subTree] === undefined)
            return false;
        else if (this.compareFunction(this[subTree], value) === 0)
            return true;
        else
            return this[subTree].contains(value);
    }

    remove(value) {
        const { subTree, subTreeCounter } = this.treeNames(value);

        if (this[subTree] === undefined)
            return false;
        else {
            const comparison = this.compareFunction(this[subTree], value);
            if (comparison === 0)
                
        }
    }
}