/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Thomas
 * @param <E>
 */
public interface Cloner<E>
{
    public E clone(E e);
}
